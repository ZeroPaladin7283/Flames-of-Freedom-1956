import { Component } from '@angular/core';
import { LoggedinfooterComponent } from "../loggedinfooter/loggedinfooter.component";
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";

@Component({
  selector: 'app-logged-in-media',
  standalone: true,
  imports: [LoggedinfooterComponent, LoggedinnavbarComponent],
  templateUrl: './logged-in-media.component.html',
  styleUrl: './logged-in-media.component.css'
})
export class LoggedInMediaComponent {

}
