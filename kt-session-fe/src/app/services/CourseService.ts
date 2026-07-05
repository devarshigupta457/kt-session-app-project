import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Course {
  id: string;
    title: string;
    price: string;
    description: string;
    link: string;
    trending?: boolean;
}   

export interface ApiResponse<T> {
  errorCode: string;
  errorDescription: string;
  data: T;
}


@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http:HttpClient) { }

 getCourses(): Observable<ApiResponse<Course[]>> {
  return this.http.get<ApiResponse<Course[]>>(
    `${environment.apiUrl}/kt-session/fetchAllCourses`
  );
}

   addCourse(course: Course): Observable<ApiResponse<any>> {
  return this.http.post<ApiResponse<any>>(
    `${environment.apiUrl}/kt-session/addCourse`,
    course
  );
}

  updateCourse(course: Course): Observable<ApiResponse<any>> {
  return this.http.put<ApiResponse<any>>(
    `${environment.apiUrl}/kt-session/updateCourse/${course.id}`,
    course
  );
}

  deleteCourse(id: string): Observable<ApiResponse<any>> {
  return this.http.delete<ApiResponse<any>>(
    `${environment.apiUrl}/kt-session/deleteCourse/${id}`
  );
}
}
