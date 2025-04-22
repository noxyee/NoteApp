import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from './components/header/header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  template: `
    <div class="h-screen flex flex-col">
      <app-header/>
      <div class="flex-1">
        <router-outlet/>
      </div>
    </div>`,
  styles: ``
})
export class AppComponent {
}
