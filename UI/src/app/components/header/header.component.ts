import {Component, signal} from '@angular/core';
import {PrimaryButtonComponent} from '../primary-button/primary-button.component';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [
    PrimaryButtonComponent,
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  title = signal('Note App');
  handleButtonClicked() {
  }
}
