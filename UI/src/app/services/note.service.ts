import {Injectable} from '@angular/core';
import {Note} from '../models/note.model';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor() {}

  createNote(): Note {
    return {
      id: (Math.random() * 1000).toFixed(0),
      title: 'New Note',
      content: 'This is a new note.',
      userId: 'user1',
      tagIds: [],
      taskIds: []
    };
  }

  updateNote(noteId: string, title: string | undefined, content: string | undefined) {

  }

  deleteNote(noteId: string) {
    console.log('Note deleted');
    return 'Note deleted successfully';
  }

  deleteTagFromNote(tagId: string) {
    console.log('Tag with id deleted', tagId)
  }

  getNotes(): Note[] {
    console.log('Fetching notes');
    return [
      {
        id: "1",
        title: 'Note 1',
        content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore.',
        userId: 'user1',
        tagIds: ['tag1', 'tag2'],
        taskIds: ['task1', 'task2']
      },
      {
        id: "2",
        title: 'Note 2',
        content: 'Content of Note 2',
        userId: 'user2',
        tagIds: ['tag1', 'tag2', 'tag3', 'tag4'],
        taskIds: ['task3']
      },
      {
        id: "3",
        title: 'Note 3',
        content: 'Content of Note 3',
        userId: 'user1',
        tagIds: ['tag1'],
        taskIds: []
      },
      {
        id: "4",
        title: 'Note 4',
        content: 'Content of Note 4',
        userId: 'user3',
        tagIds: ['tag2'],
        taskIds: ['task1']
      },
      {
        id: "5",
        title: 'Note 5',
        content: 'Content of Note 5',
        userId: 'user2',
        tagIds: [],
        taskIds: []
      },
      {
        id: "6",
        title: 'Note 6',
        content: 'Content of Note 6',
        userId: 'user1',
        tagIds: ['tag1', 'tag2'],
        taskIds: ['task2', 'task3']
      },
      {
        id: "7",
        title: 'Note 7',
        content: 'Content of Note 7',
        userId: 'user2',
        tagIds: ['tag1', 'tag2'],
        taskIds: []
      },
      {
        id: "8",
        title: 'Note 8',
        content: 'Content of Note 8',
        userId: 'user3',
        tagIds: ['tag1', 'tag2'],
        taskIds: ['task1']
      },
      {
        id: "9",
        title: 'Note 9',
        content: 'Content of Note 9',
        userId: 'user1',
        tagIds: ['tag1', 'tag2'],
        taskIds: ['task1', 'task2']
      },
      {
        id: "10",
        title: 'Note 10',
        content: 'Content of Note 10',
        userId: 'user2',
        tagIds: ['tag1', "tag2"],
        taskIds: ['task3']
      }
    ];
  }
}
