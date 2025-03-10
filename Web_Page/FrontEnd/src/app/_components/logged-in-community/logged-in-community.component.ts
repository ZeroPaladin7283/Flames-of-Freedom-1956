import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-logged-in-community',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent],
  templateUrl: './logged-in-community.component.html',
  styleUrl: './logged-in-community.component.css'
})
export class LoggedInCommunityComponent {

}
