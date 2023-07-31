import com.example.patro.model.User
import com.example.patro.repo.UserRepository
import com.example.patro.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDate

class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCreateUser() {
        val user = User(firstName = "John", lastName = "Doe", address = "123 Main St",birthday = LocalDate.of(1990, 5, 15))
        val savedUser = User(id = 1, firstName = "John", lastName = "Doe", address = "123 Main St",birthday = LocalDate.of(1990, 5, 15))

        `when`(userRepository.save(user)).thenReturn(savedUser)

        val result = userService.createUser(user)

        assertEquals(savedUser, result)
    }

    @Test
    fun testGetAllUsersWithDevices() {
        val user1 = User(id = 1, firstName = "John", lastName = "Doe", address = "123 Main St",birthday = LocalDate.of(1990, 5, 15))
        val user2 = User(id = 2, firstName = "Jane", lastName = "Smith", address = "456 Elm St",birthday = LocalDate.of(1990, 5, 15))

        val userList = listOf(user1, user2)

        `when`(userRepository.findAll()).thenReturn(userList)

        val result = userService.getAllUsersWithDevices()

        assertEquals(userList, result)
    }

    @Test
    fun testGetUserByIdExistingUser() {
        val userId = 1L
        val user = User(id = userId, firstName = "John", lastName = "Doe", address = "123 Main St", birthday = LocalDate.of(1990, 5, 15))

        `when`(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user))

        val result = userService.getUserById(userId)

        assertEquals(user, result)
    }

    @Test
    fun testGetUserByIdNonExistingUser() {
        val userId = 1L

        `when`(userRepository.findById(userId)).thenReturn(java.util.Optional.empty())

        val result = userService.getUserById(userId)

        assertNull(result)
    }

    @Test
    fun testSaveUser() {
        val user = User(firstName = "John", lastName = "Doe", address = "123 Main St", birthday = LocalDate.of(1990, 5, 15))
        val savedUser = User(id = 1, firstName = "John", lastName = "Doe", address = "123 Main St", birthday = LocalDate.of(1990, 5, 15))

        `when`(userRepository.saveAndFlush(user)).thenReturn(savedUser)

        val result = userService.saveUser(user)

        assertEquals(savedUser, result)
    }
}
