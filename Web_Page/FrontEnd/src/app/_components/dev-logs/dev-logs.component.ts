import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-dev-logs',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './dev-logs.component.html',
  styleUrl: './dev-logs.component.css'
})
export class DevLogsComponent {

}
