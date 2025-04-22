import {Component, input, output} from '@angular/core';
import {Note} from '../../models/note.model';
import {Tag} from '../../models/tag.model';

@Component({
  selector: 'app-tag',
  imports: [],
  templateUrl: './tag.component.html',
  styleUrl: './tag.component.css'
})
export class TagComponent {
  tag = input.required<Tag>()
  enableRemoveButton = input<boolean>(false)
  btnClicked = output<Tag>();
}
