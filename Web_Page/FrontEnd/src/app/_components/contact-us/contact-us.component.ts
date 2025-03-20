import { Component } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from "../navbar/navbar.component";

@Component({
  selector: 'app-contact-us',
  standalone: true,
  imports: [FooterComponent, FormsModule, NavbarComponent],
  templateUrl: './contact-us.component.html',
  styleUrl: './contact-us.component.css'
})
export class ContactUsComponent {
  contactUsername: string = "";
  contactEmail: string = "";
  contactMessage: string = "";
}
