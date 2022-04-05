import { User } from "./user"

export class CottageProfile {
    address: string
    description: string
    roomNumber: number
    bedNumber: number
    rules: string
    name: string
    cottageOwner: User
}