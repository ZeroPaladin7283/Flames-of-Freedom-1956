import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-logged-in-devlog',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent],
  templateUrl: './logged-in-devlog.component.html',
  styleUrl: './logged-in-devlog.component.css'
})
export class LoggedInDevlogComponent {

}
