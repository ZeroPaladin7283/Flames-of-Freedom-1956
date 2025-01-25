import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { LoggedinfooterComponent } from "../loggedinfooter/loggedinfooter.component";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [LoggedinnavbarComponent, LoggedinfooterComponent, FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  profileUser: string = "";
  profileEmail: string = "";
  profilePass: string = "";
  profileDesc: string = "";
}
