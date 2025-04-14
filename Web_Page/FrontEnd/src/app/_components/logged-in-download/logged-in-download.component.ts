import { Component, HostListener, OnInit } from '@angular/core';
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
  isSmallScreen: boolean = false;

  constructor(private downloadService: DownloadService){
    this.checkScreenSize();
  }

  @HostListener('window:resize', ['$event'])
    onResize() {
      this.checkScreenSize();
    }
  
    checkScreenSize() {
      this.isSmallScreen = window.innerWidth <= 425;
    }

  ngOnInit(): void{
    this.downloadService.fetchReviews().then((data) => {
      this.reviewList = data;
    });
  }

  getFirstSentenceEnd(review: string): number {
    const firstDot = review.indexOf('.');
    const firstExclamation = review.indexOf('!');
  
    if (firstDot === -1 && firstExclamation === -1) {
      return review.length;
    }
  
    if (firstDot === -1) return firstExclamation + 1;
    if (firstExclamation === -1) return firstDot + 1;
  
    return Math.min(firstDot, firstExclamation) + 1;
  }
}
