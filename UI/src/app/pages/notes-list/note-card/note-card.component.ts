import {Component, input, OnInit, signal} from '@angular/core';
import {Note} from '../../../models/note.model';
import {NgClass, SlicePipe} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {TagComponent} from '../../../components/tag/tag.component';
import {Tag} from '../../../models/tag.model';
import {TagService} from '../../../services/tag.service';

@Component({
  selector: 'app-note-card',
  imports: [
    SlicePipe,
    TagComponent
  ],
  templateUrl: './note-card.component.html',
  styleUrl: './note-card.component.css'
})
export class NoteCardComponent implements OnInit {
  note = input.required<Note>()
  tags = signal<Tag[]>([])

  constructor(private router: Router, private tagService: TagService) {
  }

  ngOnInit(): void {
    this.tags.set(this.tagService.getTags(this.note().tagIds))
  }

  navigateToNote(note: Note): void {
    this.router.navigate(['/note'], {state: {'note': note}});
  }
}
