import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';
import { NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  isNavbarCollapsed = true;

  title = 'Flames_of_Freedom_1956';
  backgroundClass: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        if (event.url === '/' || event.url.startsWith('/home') || event.url.startsWith('/download')) {
          this.backgroundClass = 'homeBG';
        } else if (event.url.startsWith('/media') ||event.url.startsWith('/dev-logs') ||event.url.startsWith('/community') ||event.url.startsWith('/contact-us') ||event.url.startsWith('/register') ||event.url.startsWith('/download') ||event.url.startsWith('/login')) {
          this.backgroundClass = 'otherBG';
        } else {
          this.backgroundClass = 'otherBG';
        }
      }
    })
  }
}
