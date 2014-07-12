exec=mini-shell
export PATH=$PATH:.
test="test_$1.txt"
execute_cmd()
{
    valgrind --leak-check=full --show-reachable=yes --log-file=valgrind.log $exec < ./_test/inputs/$test &> out
}
execute_cmd 
