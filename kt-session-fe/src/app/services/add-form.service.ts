import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Course } from './CourseService';

@Injectable({ providedIn: 'root' })
export class AddFormService {
  private openState = new BehaviorSubject<boolean>(false);
  isOpen$ = this.openState.asObservable();

  // Carries the course being edited, or null when adding a new one.
  private editCourse = new BehaviorSubject<Course | null>(null);
  editCourse$ = this.editCourse.asObservable();

  private added = new Subject<any>();
  courseAdded$ = this.added.asObservable();

  open(): void {
    this.editCourse.next(null);
    this.openState.next(true);
  }

  getCourse(): number {
    return this.editCourse.value ? 1 : 0;
  }

  openForEdit(course: Course): void {
    this.editCourse.next(course);
    this.openState.next(true);
  }

  close(): void {
    this.openState.next(false);
    this.editCourse.next(null);
  }

  notifyAdded(response?: any): void {
    this.added.next(response);
  }
}
