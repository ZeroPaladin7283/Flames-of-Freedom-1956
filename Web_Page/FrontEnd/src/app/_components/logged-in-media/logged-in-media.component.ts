import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-logged-in-media',
  standalone: true,
  imports: [FooterComponent, LoggedinnavbarComponent],
  templateUrl: './logged-in-media.component.html',
  styleUrl: './logged-in-media.component.css'
})
export class LoggedInMediaComponent {

}
