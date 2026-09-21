
package com.pitchperfect.model
import com.google.firebase.Timestamp
data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val university: String = "",
    val bio: String = "Aspiring entrepreneur",
    val profileImageUrl: String = "",
    val skills: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
    val points: Int = 50,
    val badges: List<String> = listOf("Profile Pro"),
    val createdAt: Timestamp? = null
)
data class Project(
    val projectId: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "Tech",
    val requiredSkills: List<String> = emptyList(),
    val ownerId: String = "",
    val ownerName: String = "",
    val members: List<String> = emptyList(),
    val status: String = "Open",
    val createdAt: Timestamp? = null
)
data class Match(val matchId: String = "", val userId1: String = "", val userId2: String = "", val compatibility: Double = 0.0, val createdAt: Timestamp? = null)
data class Message(val messageId: String = "", val conversationId: String = "", val senderId: String = "", val text: String = "", val timestamp: Timestamp? = null)
data class Task(val taskId: String = "", val projectId: String = "", val title: String = "", val assignedTo: String = "", val status: String = "Todo", val deadline: Timestamp? = null)
data class Mentor(val mentorId: String = "", val name: String = "", val expertise: List<String> = emptyList(), val industry: String = "", val experience: Int = 0, val verified: Boolean = true)
data class Event(val eventId: String = "", val title: String = "", val date: Timestamp? = null, val location: String = "", val category: String = "")
data class Badge(val id: String, val name: String, val pointsRequired: Int, val icon: String)
