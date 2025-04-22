export interface Note {
  id: string,
  title?: string,
  content?: string,
  userId: string,
  tagIds?: string[],
  taskIds?: string[]
}
