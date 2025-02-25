import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-logged-in-contact',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent, FormsModule],
  templateUrl: './logged-in-contact.component.html',
  styleUrl: './logged-in-contact.component.css'
})
export class LoggedInContactComponent {
  contactUsername: string = "";
  contactEmail: string = "";
  contactMessage: string = "";
}
