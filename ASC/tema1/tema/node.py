"""
    This module represents a cluster's computational node.

    Computer Systems Architecture course
    Assignment 1 - Cluster Activity Simulation
    march 2013
"""
import time
from threading import *

class Node:
    """
        Class that represents a cluster node with computation and storage functionalities.
    """
    class ProcessRequests(Thread):
        """ This thread resolves the requests from other Nodes"""
        def __init__(self, data_store, node):
            Thread.__init__(self)
            self.lock1 = node.lock1
            self.lock2 = node.lock2
            self.node = node
            self.exit_status = True
            self.lock = node.lock
            self.data_store = data_store
            self.mat_id = None
            self.row = None
            self.col = None
            self.result = 0

        def run(self):
            """ Register thread at data_store"""
            self.data_store.register_thread(self.node)

            while self.exit_status:
                """ Wait for the fields to be set """
                self.get_data()

                """ Fields were set so we can process the request"""
                self.lock1.acquire()
                if self.mat_id == 'a':
                    self.result = self.data_store.get_element_from_a(self.node, self.row, self.col)
                if self.mat_id == 'b':
                    self.result = self.data_store.get_element_from_b(self.node, self.row, self.col)
                """ Request was resolved so notify the waiting thread"""
                self.lock1.notify()
                self.lock1.release()
                
        def process_request(self, matrix_id, row, column):
            self.lock1.acquire()
            self.lock2.acquire()
            self.mat_id = matrix_id
            self.row = row
            self.col = column
            self.result = None
            """ The nedeed fields were set processrequest can wake and
                process the request"""
            self.lock2.notify()
            self.lock2.release()

            """ If result is still None wait until it is changed"""
            while self.result == None:
                self.lock1.wait()
            self.lock1.release()
            return self.result

        
        def get_data(self):
            """ Waits for the matrix_id, row and column to be set"""
            self.lock2.acquire()
            while self.result != None:
                self.lock2.wait()
            self.lock2.release()


        def exit(self):
            """ Set exit_status to False and notify lock2 if 
                it is waiting for the fields to be set"""
            self.lock2.acquire()
            self.exit_status = False
            self.result = None
            self.lock2.notify()
            self.lock2.release()


            
    def __init__(self, node_ID, block_size, matrix_size, data_store):
        """
            Constructor.

            @param node_ID: a pair of IDs uniquely identifying the node; 
            IDs are integers between 0 and matrix_size/block_size
            @param block_size: the size of the matrix blocks stored in this node's datastore
            @param matrix_size: the size of the matrix
            @param data_store: reference to the node's local data store
        """
        self.node_ID = node_ID
        self.block_size = block_size
        self.mat_size = matrix_size
        self.data_store = data_store
        self.lock1 = Condition(RLock())
        self.lock2 = Condition(RLock())
        self.lock = Lock()
        self.processor = self.ProcessRequests(self.data_store, self)
        self.processor.start()
        self.nodes = None


    def set_nodes(self, nodes):
        """
            Informs the current node of the other nodes in the cluster. 
            Guaranteed to be called before the first call to compute_matrix_block.

            @param nodes: a list containing all the nodes in the cluster
        """
        """ Create a dictionary of dictionaries that contains the nodes - access in O(1)"""
        self.nodes = nodes
        self.dict_node = dict()
        for node in self.nodes:
            (ii, jj) = node.node_ID
            if ii not in self.dict_node:
                self.dict_node[ii] = dict()
            self.dict_node[ii][jj] = node


    def process_request(self, matrix_id, row, column):
        return self.processor.process_request(matrix_id, row, column)


    def compute_matrix_block(self, start_row, start_column, num_rows, num_columns):
        """
            Computes a given block of the result matrix.
            The method invoked by FEP nodes.

            @param start_row: the index of the first row in the block
            @param start_column: the index of the first column in the block
            @param num_rows: number of rows in the block
            @param num_columns: number of columns in the block

            @return: the block of the result matrix encoded as a row-order list of lists of integers
        """
        mat = []
        elem_a = None
        elem_b = None
        res = 0
        for i in range(start_row, start_row + num_rows):
            mat.append([])
            for j in range(start_column, start_column + num_columns):
                for k in range(0, self.mat_size):
                    elem_a = None
                    elem_b = None
                    """ Get element from matrix a"""
                    node = self.dict_node[i/self.block_size][k/self.block_size]
                    (ii, jj) = node.node_ID
                    node.lock.acquire()
                    elem_a = node.process_request('a', i - (ii * self.block_size), k - (jj * self.block_size))
                    node.lock.release()
                    """ Get element from matrix b"""
                    node = self.dict_node[k/self.block_size][j/self.block_size]
                    (ii, jj) = node.node_ID
                    node.lock.acquire()
                    elem_b = node.process_request('b', k - (ii * self.block_size), j - (jj * self.block_size))
                    node.lock.release()
                    """ Compute result """
                    res = res + (elem_a * elem_b)
                """ Place the result in the matrix """
                mat[i - start_row].append(res)
                res = 0
        return mat
        

    def shutdown(self):
        """
            Instructs the node to shutdown (terminate all threads).
        """
        self.processor.exit()
        self.processor.join()


