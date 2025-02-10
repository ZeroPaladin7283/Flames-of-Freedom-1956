import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from '../footer/footer.component';
import { FormsModule } from '@angular/forms';
import { ContactService } from '../../_services/contact.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-contact-us',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './contact-us.component.html',
  styleUrl: './contact-us.component.css'
})
export class ContactUsComponent {
  contactUsername: string = "";
  contactEmail: string = "";
  contactMessage: string = "";
  errorMessage: string = "";

  constructor(private contactService: ContactService) {}

  onSubmit() {
    if (!this.contactUsername || !this.contactEmail || !this.contactMessage) {
      this.errorMessage = 'Please fill in all fields.';
      return;
    }

    if(this.contactService.contactFunc(this.contactUsername, this.contactEmail, this.contactMessage)) {
      this.contactUsername = "";
      this.contactEmail = "";
      this.contactMessage = "";
      return alert("Successfully submitted!")
    } else {
      this.errorMessage = 'Invalid username or email'
    }
  }
}
