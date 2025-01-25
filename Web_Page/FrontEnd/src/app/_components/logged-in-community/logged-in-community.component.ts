import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { LoggedinfooterComponent } from "../loggedinfooter/loggedinfooter.component";

@Component({
  selector: 'app-logged-in-community',
  standalone: true,
  imports: [LoggedinnavbarComponent, LoggedinfooterComponent],
  templateUrl: './logged-in-community.component.html',
  styleUrl: './logged-in-community.component.css'
})
export class LoggedInCommunityComponent {

}
