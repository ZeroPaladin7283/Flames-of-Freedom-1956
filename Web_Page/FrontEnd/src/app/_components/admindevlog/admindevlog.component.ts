import { Component } from '@angular/core';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';
import { FooterComponent } from '../footer/footer.component';

@Component({
  selector: 'app-admindevlog',
  standalone: true,
  imports: [AdminnavbarComponent, FooterComponent],
  templateUrl: './admindevlog.component.html',
  styleUrl: './admindevlog.component.css'
})
export class AdmindevlogComponent {

}
