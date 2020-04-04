package men.ngopi.zain.aroundme.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Observable
import men.ngopi.zain.aroundme.RxImmediateSchedulerRule
import men.ngopi.zain.aroundme.data.model.PointLocation
import men.ngopi.zain.aroundme.data.repository.Repository
import men.ngopi.zain.aroundme.ui.home.HomeViewModel
import men.ngopi.zain.aroundme.util.Constant.ERROR_MESSAGE
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @MockK
    lateinit var repository: Repository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(repository)
    }

    @Test
    fun `given list of points, when get points, then update live data`() {
        // Given
        val mockedObserver = createPeopleObserver()
        homeViewModel.points.observeForever(mockedObserver)

        every { repository.getPoints(createDummyPointLocation()) } returns
                Observable.just(createDummyPointLocationList())

        // When
        homeViewModel.getPoints(createDummyPointLocation())

        // Then
        val pointLocationList = mutableListOf<List<PointLocation>>()
        verify { mockedObserver.onChanged(capture(pointLocationList)) }

        Truth.assertThat(pointLocationList[0].size).isEqualTo(createDummyPointLocationList().size)

        verify { repository.getPoints(createDummyPointLocation()) }
    }

    @Test
    fun `given error message, when get points, then update live data`() {
        // Given
        val mockedObserver = createMessageObserver()
        homeViewModel.message.observeForever(mockedObserver)

        every { repository.getPoints(createDummyPointLocation()) } returns
                Observable.error(Throwable())

        // When
        homeViewModel.getPoints(createDummyPointLocation())

        // Then
        val message = mutableListOf<String>()
        verify { mockedObserver.onChanged(capture(message)) }

        Truth.assertThat(message[0]).isEqualTo(ERROR_MESSAGE)

        verify { repository.getPoints(createDummyPointLocation()) }
    }

    private fun createPeopleObserver(): Observer<List<PointLocation>> =
        spyk(Observer<List<PointLocation>> {})

    private fun createMessageObserver(): Observer<String> = spyk(Observer<String> {})

    private fun createDummyPointLocation(): PointLocation {
        return PointLocation(
            lat = -1.797,
            long = -10.908,
            name = "Test"
        )
    }

    private fun createDummyPointLocationList(): List<PointLocation> {
        val people = mutableListOf<PointLocation>()
        for (x in 1..5) {
            people.add(createDummyPointLocation())
        }
        return people
    }

}
