import {Component, input, OnInit, signal} from '@angular/core';
import {Task} from '../../models/task.model';
import {TagComponent} from '../tag/tag.component';
import {Tag} from '../../models/tag.model';
import {Router} from '@angular/router';
import {TagService} from '../../services/tag.service';

@Component({
  selector: 'app-task',
  imports: [
    TagComponent
  ],
  templateUrl: './task-card.component.html',
  styleUrl: './task-card.component.css'
})
export class TaskCardComponent implements OnInit {
  task = input.required<Task>();
  tags = signal<Tag[]>([]);

  constructor(private router: Router, private tagService: TagService) {
  }

  ngOnInit(): void {
    this.tags.set(this.tagService.getTags(this.task().tagIds))
  }

  navigateToTask(task: Task): void {
    this.router.navigate(['/task'], {state: {'task': task}});
  }
}
