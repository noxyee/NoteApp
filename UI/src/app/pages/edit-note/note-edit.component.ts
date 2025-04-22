import {Component, HostListener, signal} from '@angular/core';
import {Router} from '@angular/router';
import {Note} from '../../models/note.model';
import {NoteService} from '../../services/note.service';
import {TagComponent} from '../../components/tag/tag.component';
import {Tag} from '../../models/tag.model';
import {TagService} from '../../services/tag.service';
import {Task} from '../../models/task.model';
import {TaskService} from '../../services/task.service';
import {TaskCardComponent} from '../../components/task-card/task-card.component';

@Component({
  selector: 'app-note-edit',
  imports: [
    TagComponent,
    TaskCardComponent
  ],
  templateUrl: './note-edit.component.html',
  styleUrl: './note-edit.component.css'
})
export class NoteEditComponent {
  note = signal<Note | null>(null);
  tags = signal<Tag[]>([]);
  tasks = signal<Task[]>([]);

  constructor(private router: Router, private noteService: NoteService, private tagService: TagService, private taskService: TaskService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state?.['note']) {
      this.note.set(navigation.extras.state['note']);
      if (this.note()?.tagIds)
        this.tags.set(this.tagService.getTags(this.note()!.tagIds!));
      if (this.note()?.taskIds)
        this.tasks.set(this.taskService.getTasks(this.note()!.taskIds!));
      console.log(this.tasks().length)
    }
  }

  @HostListener('window:beforeunload')
  beforeUnload(): void {
    this.noteService.updateNote(this.note()!!.id, this.note()?.title, this.note()?.content);
  }

  removeTagClicked(tag: Tag): void {
    this.noteService.deleteTagFromNote(tag.id);
    const updatedTags = this.tags()?.filter(t => t.id !== tag.id);
    this.tags.set(updatedTags || []);
  }
}
