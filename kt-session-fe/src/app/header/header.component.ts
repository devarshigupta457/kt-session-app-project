import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  showProfile = false;

  constructor(public cartService: CartService, public auth: AuthService, private router: Router){}

  ngOnInit(): void {

  }

  toggleProfile(): void {
    this.showProfile = !this.showProfile;
  }

  logout(): void {
    this.showProfile = false;
    this.auth.logout();
    this.router.navigate(['/']);
  }

}
