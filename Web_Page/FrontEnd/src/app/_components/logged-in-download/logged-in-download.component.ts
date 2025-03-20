import { Component, OnInit } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { DownloadService } from '../../_services/download.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-logged-in-download',
  standalone: true,
  imports: [LoggedinnavbarComponent, CommonModule],
  templateUrl: './logged-in-download.component.html',
  styleUrl: './logged-in-download.component.css'
})
export class LoggedInDownloadComponent implements OnInit{
  reviewList: any[] = [];

  constructor(private downloadService: DownloadService){}

  ngOnInit(): void{
    this.downloadService.fetchReviews().then((data) => {
      this.reviewList = data;
    });
  }
}
