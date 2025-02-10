import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from '../loggedinnavbar/loggedinnavbar.component';

@Component({
  selector: 'app-logged-in-home',
  standalone: true,
  imports: [LoggedinnavbarComponent],
  templateUrl: './logged-in-home.component.html',
  styleUrl: './logged-in-home.component.css'
})
export class LoggedInHomeComponent {

}
