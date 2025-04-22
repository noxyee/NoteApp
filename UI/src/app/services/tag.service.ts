import {Injectable} from '@angular/core';
import {Tag} from '../models/tag.model';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor() {}

  getTags(tags: string[] | undefined): Tag[] {
    if (!tags) {
      return []
    }
    const userTags = [
      {id: 'tag1', name: 'Tag 1', color: '#A1C3D1', userId: 'user1'},
      {id: 'tag2', name: 'Tag 2', color: '#F4A261', userId: 'user1'},
      {id: 'tag3', name: 'Tag 3', color: '#6A0572', userId: 'user1'},
      {id: 'tag4', name: 'Tag 4', color: '#2A9D8F', userId: 'user1'},
    ];

    return userTags.filter(tag => tags.includes(tag.id));
  }
}
