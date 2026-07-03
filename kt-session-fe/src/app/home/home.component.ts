import { Component } from '@angular/core';
import { AddFormService } from '../services/add-form.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private addForm: AddFormService, public auth: AuthService) {}

  addNewCourse(): void {
    this.addForm.open();
  }

}
