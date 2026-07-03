import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  username = '';
  email = '';
  password = '';
  confirmPassword = '';
  fullName = '';
  error = '';
  loading = false;

  constructor(private auth: AuthService, private router: Router) {}

  submit(): void {
    if (!this.username.trim() || !this.email.trim() || !this.password) {
      this.error = 'Please fill in all fields.';
      return;
    }
    if (this.password !== this.confirmPassword) {
      this.error = 'Passwords do not match.';
      return;
    }
    this.error = '';
    this.loading = true;
    this.auth.register(this.username.trim(), this.email.trim(), this.password, this.fullName).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/courses']);
      },
      error: (err) => {
        this.loading = false;
        this.error = err?.error?.message || 'Signup failed. Try a different username.';
      }
    });
  }
}
