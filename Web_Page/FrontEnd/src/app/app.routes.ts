import { Routes } from '@angular/router';
import { HomeComponent } from './_components/home/home.component';
import { MediaComponent } from './_components/media/media.component';
import { DevLogsComponent } from './_components/dev-logs/dev-logs.component';
import { CommunityComponent } from './_components/community/community.component';
import { ContactUsComponent } from './_components/contact-us/contact-us.component';
import { RegisterComponent } from './_components/register/register.component';
import { DownloadComponent } from './_components/download/download.component';
import { LoginComponent } from './_components/login/login.component';
import { AdmincommunityComponent } from './_components/admincommunity/admincommunity.component';
import { AdmindevlogComponent } from './_components/admindevlog/admindevlog.component';
import { LoggedInCommunityComponent } from './_components/logged-in-community/logged-in-community.component';
import { LoggedInContactComponent } from './_components/logged-in-contact/logged-in-contact.component';
import { LoggedInDevlogComponent } from './_components/logged-in-devlog/logged-in-devlog.component';
import { LoggedInDownloadComponent } from './_components/logged-in-download/logged-in-download.component';
import { LoggedInHomeComponent } from './_components/logged-in-home/logged-in-home.component';
import { LoggedInMediaComponent } from './_components/logged-in-media/logged-in-media.component';
import { ProfileComponent } from './_components/profile/profile.component';
import { NotFoundComponent } from './_components/not-found/not-found.component';

export const routes: Routes = [
    /* Pages routes */
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component:HomeComponent},
    {path: 'media', component:MediaComponent},
    {path: 'dev-logs', component:DevLogsComponent},
    {path: 'community', component:CommunityComponent},
    {path: 'contact-us', component:ContactUsComponent},
    {path: 'register', component:RegisterComponent},
    {path: 'download', component:DownloadComponent},
    {path: 'login', component:LoginComponent},
    
    /* Admin pages routes */
    {path: 'admincommunity', component:AdmincommunityComponent},
    {path: 'admindevlog', component:AdmindevlogComponent},
    
    /* Logged in pages routes */
    {path: 'logged-in-community', component:LoggedInCommunityComponent},
    {path: 'logged-in-contact', component:LoggedInContactComponent},
    {path: 'logged-in-devlog', component:LoggedInDevlogComponent},
    {path: 'logged-in-download', component:LoggedInDownloadComponent},
    {path: 'logged-in-home', component:LoggedInHomeComponent},
    {path: 'logged-in-media', component:LoggedInMediaComponent},
    {path: 'profile', component:ProfileComponent},

    /* Nem létező oldal */
    {path: '**', pathMatch:'full', component:NotFoundComponent}
];
