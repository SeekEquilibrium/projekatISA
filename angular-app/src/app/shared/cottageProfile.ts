import { User } from "./user"

export class CottageProfile {
    id: number
    address: string
    description: string
    roomNumber: number
    bedNumber: number
    rules: string
    name: string
    cottageOwner: User
}