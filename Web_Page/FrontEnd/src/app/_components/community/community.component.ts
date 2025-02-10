import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

@Component({
  selector: 'app-community',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './community.component.html',
  styleUrl: './community.component.css'
})
export class CommunityComponent {

}
