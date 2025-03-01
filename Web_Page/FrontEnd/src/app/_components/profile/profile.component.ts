import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent, FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  profileUser: string = "";
  profileEmail: string = "";
  profilePass: string = "";
  profileDesc: string = "";

  constructor(private router: Router) {}

  onLogout() {
    this.router.navigate(['home']);
  }
}