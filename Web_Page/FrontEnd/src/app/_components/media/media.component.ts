import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-media',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './media.component.html',
  styleUrl: './media.component.css'
})
export class MediaComponent {

}
