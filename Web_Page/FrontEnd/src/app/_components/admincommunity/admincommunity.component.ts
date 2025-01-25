import { Component } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';

@Component({
  selector: 'app-admincommunity',
  standalone: true,
  imports: [AdminnavbarComponent ,FooterComponent],
  templateUrl: './admincommunity.component.html',
  styleUrl: './admincommunity.component.css'
})
export class AdmincommunityComponent {

}
