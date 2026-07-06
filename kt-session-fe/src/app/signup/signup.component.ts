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

  private readonly emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  private readonly passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{4,}$/;

  constructor(private auth: AuthService, private router: Router) {}

  submit(): void {
    const username = this.username.trim();
    const email = this.email.trim();
    const fullName = this.fullName.trim();

    if (!username || !email || !this.password || !this.confirmPassword || !fullName) {
      this.error = 'Please fill in all fields.';
      return;
    }

    if (!this.emailPattern.test(email)) {
      this.error = 'Please enter a valid email address.';
      return;
    }

    if (!this.passwordPattern.test(this.password)) {
      this.error = 'Password must be at least 4 characters and include uppercase, lowercase, number, and special character.';
      return;
    }

    if (this.password !== this.confirmPassword) {
      this.error = 'Passwords do not match.';
      return;
    }

    this.error = '';
    this.loading = true;

    this.auth.sendSignupOtp(username, email, this.password, fullName).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/verify-otp'], { queryParams: { email } });
      },
      error: (err) => {
        this.loading = false;
        this.error = err?.error?.message || 'Signup failed. Please try again.';
      }
    });
  }

  get passwordCriteria(): string {
    return 'Use 4+ chars with uppercase, lowercase, number and special character.';
  }
}
