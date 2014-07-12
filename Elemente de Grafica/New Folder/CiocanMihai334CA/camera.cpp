#include "camera.h"

Camera::Camera(){
}
Camera::~Camera(){
}

void Camera::init(){
	position = Vector3D(0,0,3);
	forward = Vector3D(0,0,-1);
	up = Vector3D(0,1,0);
	right = Vector3D(1,0,0);
}

void Camera::translate_Forward(float dist){
	position = position + forward * dist;
}
void Camera::translate_Up(float dist){
	position = position + up * dist;
}
void Camera::translate_Right(float dist){
	position = position + right * dist;
}

void Camera::rotateFPS_OY(float angle){
	forward = forward * cos(angle) + right * sin(angle);
	forward.Normalize();
	right = forward.CrossProduct(up);
	right.Normalize();
}
void Camera::rotateFPS_OX(float angle){
	up = up * cos(angle) + forward * sin(angle);
	up.Normalize();
	forward = up.CrossProduct(right);
	forward.Normalize();
}
void Camera::rotateFPS_OZ(float angle){
	up = up * cos(angle) + right * sin(angle);
	up.Normalize();
	right = up.CrossProduct(forward);
	right.Normalize();
}
void Camera::rotateTPS_OY(float angle, float dist_to_interes){
	Vector3D new_fw = forward * cos(angle) + right*sin(angle);
	Vector3D new_right = new_fw.CrossProduct(up);
	Vector3D newObs = position + (forward *dist_to_interes) - (new_fw * dist_to_interes);
	forward = new_fw;
	right = new_right;
	position = newObs;
}
void Camera::rotateTPS_OX(float angle, float dist_to_interes){
	Vector3D new_up = up * cos(angle) + forward*sin(angle);
	Vector3D new_fwd = new_up.CrossProduct(right);
	new_fwd.Normalize();
	Vector3D newObs = position + (up *dist_to_interes) - (new_up * dist_to_interes);
	up = new_up;
	forward= new_fwd;
	position = newObs;
}
void Camera::rotateTPS_OZ(float angle, float dist_to_interes){
	Vector3D new_up = up * cos(angle) + right*sin(angle);
	Vector3D new_right = new_up.CrossProduct(forward);
	new_right.Normalize();
	Vector3D newObs = position + up *dist_to_interes - new_up * dist_to_interes;
	up = new_up;
	right = new_right;
	position = newObs;
}


void Camera::render(){
	Vector3D center = position + forward;
	gluLookAt(	position.x, position.y, position.z, 
				center.x, center.y, center.z,
				up.x, up.y, up.z);
}