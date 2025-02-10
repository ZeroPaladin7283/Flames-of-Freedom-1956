import { Component } from '@angular/core';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';
import { FooterComponent } from '../footer/footer.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admincontact',
  standalone: true,
  imports: [AdminnavbarComponent, FooterComponent, FormsModule],
  templateUrl: './admincontact.component.html',
  styleUrl: './admincontact.component.css'
})
export class AdmincontactComponent {
  contactUsername: string = "";
  contactEmail: string = "";
  contactMessage: string = "";
}
