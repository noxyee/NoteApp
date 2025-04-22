export interface Task {
  id: string,
  title: string,
  content: string,
  userId: string,
  noteId: string,
  tagIds: string[],
  dueDate?: Date,
  done: Boolean,
  sendMailAfterDueDate: Boolean
}
