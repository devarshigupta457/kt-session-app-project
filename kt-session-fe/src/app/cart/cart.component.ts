import { Component } from '@angular/core';
import { CartService, CartItem } from '../services/cart.service';
import { Course } from '../services/CourseService';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {

    courses: Course[] = [];


  constructor(public cartService: CartService,
        private router: Router,
    
  ) {}

  get items(): CartItem[] {
    return this.cartService.getItems();
  }

  remove(id: number): void {
    this.cartService.removeItem(id);
  }

  openCourse(course: Course, event?: Event): void {
    event?.stopPropagation();
    this.router.navigate(['/course-view'], { queryParams: { src: course.link } });
  }
}
