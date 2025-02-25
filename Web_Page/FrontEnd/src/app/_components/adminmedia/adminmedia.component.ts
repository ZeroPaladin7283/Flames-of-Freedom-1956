import { Component } from '@angular/core';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-adminmedia',
  standalone: true,
  imports: [AdminnavbarComponent, FooterComponent],
  templateUrl: './adminmedia.component.html',
  styleUrl: './adminmedia.component.css'
})
export class AdminmediaComponent {

}
