import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { DownloadService } from '../../_services/download.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-download',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './download.component.html',
  styleUrl: './download.component.css'
})
export class DownloadComponent implements OnInit{
  reviewList: any[] = [];

  constructor(private downloadService: DownloadService){}

  ngOnInit(): void{
    this.downloadService.fetchReviews().then((data) => {
      this.reviewList = data;
    });
  }
}