import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { LoggedinfooterComponent } from "../loggedinfooter/loggedinfooter.component";

@Component({
  selector: 'app-logged-in-devlog',
  standalone: true,
  imports: [LoggedinnavbarComponent, LoggedinfooterComponent],
  templateUrl: './logged-in-devlog.component.html',
  styleUrl: './logged-in-devlog.component.css'
})
export class LoggedInDevlogComponent {

}
