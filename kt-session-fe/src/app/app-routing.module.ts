import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CoursesComponent } from './courses/courses.component';
import { CourseViewComponent } from './course-view/course-view.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { CartComponent } from './cart/cart.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    data:{ animation:'home' },
  },
  {
    path:'courses',
    component:CoursesComponent,
    data:{ animation:'courses' },
  },
  {
    path:'course-view',
    component:CourseViewComponent,
    canActivate:[AuthGuard],
    data:{ animation:'course-view' },
  },
  {
    path:'login',
    component:LoginComponent,
    data:{ animation:'login' },
  },
  {
    path:'signup',
    component:SignupComponent,
    data:{ animation:'signup' },
  },
  {
    path:'cart',
    component:CartComponent,
    canActivate:[AuthGuard],
    data:{ animation:'cart' },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
