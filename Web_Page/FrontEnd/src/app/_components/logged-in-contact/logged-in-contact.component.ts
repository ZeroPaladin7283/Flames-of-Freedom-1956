import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { LoggedinfooterComponent } from "../loggedinfooter/loggedinfooter.component";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-logged-in-contact',
  standalone: true,
  imports: [LoggedinnavbarComponent, LoggedinfooterComponent, FormsModule],
  templateUrl: './logged-in-contact.component.html',
  styleUrl: './logged-in-contact.component.css'
})
export class LoggedInContactComponent {
  contactUsername: string = "";
  contactEmail: string = "";
  contactMessage: string = "";
}
