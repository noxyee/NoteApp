import { Injectable } from '@angular/core';
import {Task} from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor() { }

  getTasks(tasks: string[]) : Task[] {
    const tasksList: Task[] = [
      {
        id: 'task1',
        title: 'Task 1',
        content: 'Task 1 content',
        userId: 'user1',
        tagIds: ['tag1', 'tag2'],
        noteId: '',
        done: false,
        sendMailAfterDueDate: false,
        dueDate: new Date()
      },
      {
        id: 'task2',
        title: 'Task 2',
        content: 'Task 2 content',
        userId: 'user2',
        tagIds: ['tag3', 'tag4'],
        noteId: '',
        done: true,
        sendMailAfterDueDate: true
      },
    ]
    return tasksList.filter(task => tasks.includes(task.id));
  }
}
