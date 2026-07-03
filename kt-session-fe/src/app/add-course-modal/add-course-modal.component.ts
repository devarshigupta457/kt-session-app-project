import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CourseService, Course } from '../services/CourseService';
import { AddFormService } from '../services/add-form.service';

@Component({
  selector: 'app-add-course-modal',
  templateUrl: './add-course-modal.component.html',
  styleUrls: ['./add-course-modal.component.css']
})
export class AddCourseModalComponent implements OnInit, OnDestroy {

  isOpen = false;
  saving = false;
  editMode = false;
  newCourse: Course = this.emptyCourse();
  private sub?: Subscription;
  private editSub?: Subscription;

  constructor(private courseService: CourseService, private addForm: AddFormService) {}

  ngOnInit(): void {
    this.sub = this.addForm.isOpen$.subscribe(open => {
      this.isOpen = open;
      if (open) {
        this.saving = false;
      }
    });
    // Prefill and switch to edit mode when a course is passed in.
    this.editSub = this.addForm.editCourse$.subscribe(course => {
      this.editMode = !!course;
      this.newCourse = course ? { ...course } : this.emptyCourse();
    });
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
    this.editSub?.unsubscribe();
  }

  private emptyCourse(): Course {
    return { id: 0, title: '', description: '', price: '', link: '', trending: false };
  }

  @HostListener('document:keydown.escape')
  close(): void {
    if (this.isOpen) {
      this.addForm.close();
    }
  }

  addCourse(): void {
 if (!this.newCourse.title.trim() || !this.newCourse.link.trim()) {
      return;
    }
    
    this.saving = true;
    const request$ = this.editMode
      ? this.courseService.updateCourse(this.newCourse)
      : this.courseService.addCourse(this.newCourse);
    request$.subscribe({
      next: (response) => {
        this.addForm.notifyAdded(response);
        this.addForm.close();
      },
      error: () => { this.saving = false; }
    });
  }
}
