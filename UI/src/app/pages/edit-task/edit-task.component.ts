import {Component, signal} from '@angular/core';
import {Router} from '@angular/router';
import {Task} from '../../models/task.model';

@Component({
  selector: 'app-edit-task',
  imports: [],
  templateUrl: './edit-task.component.html',
  styleUrl: './edit-task.component.css'
})
export class EditTaskComponent {
  task = signal<Task | null>(null)

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state?.['task']) {
      this.task.set(navigation.extras.state['task'])
    }
  }
}
