
package com.pitchperfect.data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.pitchperfect.model.*
import kotlinx.coroutines.tasks.await
class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    suspend fun register(email: String, password: String, user: User): String {
        if (!email.endsWith(".ac.za")) throw Exception("Use university email (.ac.za)")
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user!!.uid
        db.collection("users").document(uid).set(user.copy(userId = uid)).await()
        auth.currentUser?.sendEmailVerification()?.await()
        return uid
    }
    suspend fun login(email: String, password: String) = auth.signInWithEmailAndPassword(email, password).await()
    fun logout() = auth.signOut()
    fun calculateCompatibility(userSkills: List<String>, required: List<String>): Double {
        if (required.isEmpty()) return 75.0
        val intersect = userSkills.intersect(required.toSet()).size
        return (intersect.toDouble() / required.size * 100).coerceIn(0.0, 95.0) + (5..15).random()
    }
    suspend fun createProject(project: Project): String {
        val doc = db.collection("projects").document()
        db.collection("projects").document(doc.id).set(project.copy(projectId = doc.id)).await()
        addPoints(project.ownerId, 25)
        return doc.id
    }
    suspend fun getProjects() = db.collection("projects").get().await().toObjects(Project::class.java)
    suspend fun joinProject(projectId: String, userId: String) {
        db.collection("projects").document(projectId).update("members", FieldValue.arrayUnion(userId)).await()
        addPoints(userId, 25)
    }
    suspend fun sendMessage(msg: Message) { db.collection("messages").add(msg).await() }
    suspend fun addPoints(userId: String, points: Int) {
        db.collection("users").document(userId).update("points", FieldValue.increment(points.toLong())).await()
    }
    suspend fun createTask(task: Task) { db.collection("tasks").add(task).await() }
    suspend fun completeTask(taskId: String, userId: String) {
        db.collection("tasks").document(taskId).update("status", "Done").await()
        addPoints(userId, 20)
    }
}
