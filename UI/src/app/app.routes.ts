import {Routes} from '@angular/router';
import {NotesListComponent} from './pages/notes-list/notes-list.component';
import {NoteEditComponent} from './pages/edit-note/note-edit.component';
import {EditTaskComponent} from './pages/edit-task/edit-task.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: NotesListComponent,
  },
  {
    path: 'note',
    component: NoteEditComponent
  },
  {
    path: 'task',
    component: EditTaskComponent
  }
];
