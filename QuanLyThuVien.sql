--create database [QuanLyThuVien]
ALTER DATABASE [QuanLyThuVien] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyThuVien].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyThuVien] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyThuVien]  SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyThuVien]  SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyThuVien]  SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyThuVien] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyThuVien] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyThuVien] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyThuVien] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyThuVien] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyThuVien] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyThuVien] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyThuVien] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyThuVien] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyThuVien] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyThuVien] SET QUERY_STORE = OFF
GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****** KHO SÁCH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhoSach](
    [maSach] [varchar](5) NOT NULL,
    [tongSoLuong] [int] NULL,
    [soLuongCon] [int] NULL,
    [soLuongSachHong] [int] NULL,
    PRIMARY KEY CLUSTERED ([maSach] ASC)
) ON [PRIMARY];
GO
/******** THÔNG TIN SÁCH ************/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThongTinSach](
    [maSach] [varchar](5) NOT NULL,
    [tenSach] [nvarchar](100) NULL,
    [maDMSach] [char](10) NULL,
    [maTheLoai] [char](10) NOT NULL,
    [maTacGia] [nvarchar](100) NOT NULL,
    [NXB] [nvarchar](100) NOT NULL,
    [namXuatBan] [int] NULL,
    [giaTienSach] [money] NOT NULL,
	[tinhTrangSach] [nvarchar](100) NULL,
    [tomTatND] [ntext] NULL,

    PRIMARY KEY CLUSTERED ([maSach] ASC)
) ON [PRIMARY];
GO
ALTER TABLE [dbo].[ThongTinSach] WITH CHECK ADD FOREIGN KEY([maSach])
REFERENCES [dbo].[KhoSach] ([maSach])
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/********************* TÁC GIẢ ***********************/
CREATE TABLE [dbo].[TacGia] (
	[maTacGia] [nvarchar](100) NOT NULL,
	[tenTacGia] [nvarchar](100) NOT NULL,
	[soSach] [int] NOT NULL,
	PRIMARY KEY CLUSTERED 
(
	[maTacGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE [dbo].[ThongTinSach]
ADD CONSTRAINT [FK__ThongTinS__maTacGia] FOREIGN KEY ([maTacGia]) REFERENCES [dbo].[TacGia] ([maTacGia]);
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****** QUẢN LÝ ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuanLy](
	[maQuanLy] [char](10) NOT NULL,
	[matKhau] [varchar](20) NULL,
	[tenQuanLy] [nvarchar](100) NULL,
	[ngaySinh] [date] NULL,
	[gioiTinh] [nvarchar](3) NULL,
	[diaChi] [nvarchar](100) NULL,
	[sdt] [char](11) NULL,
	[email] [varchar](100) NULL,
	[trangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maQuanLy] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****** DANH MỤC SÁCH   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DanhMucSach](
	[maDMSach] [char](10) NOT NULL,
	[tenDMSach] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[maDMSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/******  TÀI KHOẢN    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTaiKhoan] [char](10) NOT NULL,
	[matKhau] [varchar](20) NULL,
    [loaiTaiKhoan] [nvarchar](100) NULL,
	[tenNguoiDung] [nvarchar](100) NULL,
	[ngaySinh] [date] NULL,
	[gioiTinh] [nvarchar](3) NULL,
	[email] [varchar](100) NULL,
	[sdt] [char](11) NULL,
	[diaChi] [nvarchar](100) NULL,
	[trangThai] [int] NOT NULL,
	[soLuongMuon] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maTaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
--Ràng buộc CHECK cột "trangThai" để đảm bảo rằng giá trị chỉ có thể là 1 hoặc 0.
ALTER TABLE [dbo].[TaiKhoan] ADD CONSTRAINT CK_TaiKhoan_trangThai CHECK (trangThai IN (1, 0));
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*********** LOẠI THẺ **********/
-- Tạo bảng LoaiThe
CREATE TABLE [dbo].[LoaiThe](
    [maLoaiThe] [char](10) NOT NULL,  
    [tenLoaiThe] [nvarchar](100) NOT NULL, 
    [ngayMoThe] [date] NOT NULL,
    [hanSuDung] [date] NOT NULL, 
    [soSachDuocMuon] [int] NOT NULL, 
    [thoiGianDuocMuonToiDa] [int] NOT NULL, -- Thời gian mượn tối đa (ngày)
    [giaTienGiaHan] [money] NOT NULL, -- Giá tiền để gia hạn thẻ
    PRIMARY KEY CLUSTERED 
    (
        [maLoaiThe] ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
-- Tạo ràng buộc khóa ngoại
ALTER TABLE [dbo].[LoaiThe] ADD CONSTRAINT FK_LoaiThe_maLoaiThe FOREIGN KEY (maLoaiThe) REFERENCES [dbo].[TaiKhoan] (maTaiKhoan);
GO
----------------------------/* NÀY MỚI THÊM */----------------------------------
DECLARE @SoCuoiHSSV INT;
DECLARE @SoCuoiCBGV INT;
SELECT @SoCuoiHSSV = ISNULL(MAX(CASE WHEN [loaiTaiKhoan] = 'Học Sinh - Sinh Viên' THEN CAST(RIGHT([maTaiKhoan], 4) AS INT) END), 0),
       @SoCuoiCBGV = ISNULL(MAX(CASE WHEN [loaiTaiKhoan] = 'Cán Bộ - Giáo Viên' THEN CAST(RIGHT([maTaiKhoan], 4) AS INT) END), 0)
FROM [dbo].[TaiKhoan];
BEGIN TRAN;

UPDATE [dbo].[TaiKhoan]
SET [maTaiKhoan] = CASE 
    WHEN [loaiTaiKhoan] = 'Học Sinh - Sinh Viên ' THEN '3121' + RIGHT('0000' + CAST(@SoCuoiHSSV + 1 AS VARCHAR(4)), 4)
    WHEN [loaiTaiKhoan] = 'Cán Bộ - Giáo Viên' THEN '3120' + RIGHT('0000' + CAST(@SoCuoiCBGV + 1 AS VARCHAR(4)), 4)
    ELSE [maTaiKhoan] 
END
WHERE [loaiTaiKhoan] IN ('Học Sinh - Sinh Viên', 'Cán Bộ - Giáo Viên');

COMMIT TRAN;
--------------------------------------------
--Check số sách được mượn 
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[CheckSoLuongSach] (@maTaiKhoan CHAR(13), @soLuongSach INT)
RETURNS BIT
AS
BEGIN
    DECLARE @maxSoSachDuocMuon INT

    SELECT @maxSoSachDuocMuon = 
    (
        SELECT TOP 1 LT.soSachDuocMuon
        FROM [dbo].[LoaiThe] LT
        INNER JOIN [dbo].[TaiKhoan] T ON LT.maLoaiThe = T.maTaiKhoan
        WHERE T.maTaiKhoan = @maTaiKhoan
    )

    RETURN CASE WHEN @soLuongSach <= @maxSoSachDuocMuon THEN 1 ELSE 0 END
END;
GO
-- Cập nhật trạng thái tài khoản sang 1 (khóa) nếu thẻ hết hạn
UPDATE [dbo].[TaiKhoan]
SET trangThai = 1
WHERE maTaiKhoan IN (
    SELECT maTaiKhoan
    FROM [dbo].[LoaiThe]
    WHERE hanSuDung < GETDATE()
);
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****** PHÂN LOẠI SÁCH  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanLoaiSach](
    [maTheLoai] [char](10) NOT NULL,
    [tenTheLoai] [nvarchar](100) NOT NULL,
    PRIMARY KEY CLUSTERED 
    (
        [maTheLoai] ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO 
ALTER TABLE [dbo].[ThongTinSach] ADD CONSTRAINT FK_LoaiThe_maTheLoai
FOREIGN KEY (maTheLoai) REFERENCES [dbo].[PhanLoaiSach](maTheLoai);
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****** PHIẾU MƯỢN   ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuMuon](
	[maPhieuMuon] [varchar](5) NOT NULL,
	[ngayMuon] [date] NULL,
	[soNgayMuon] [int] NULL,
	[hanTraSach] [date] NULL,
	[soLuongSach] [int] NULL,
	[maTaiKhoan] [char](10) NULL,
	[maQuanLy] [char](10) NULL,
	[ghiChu] [nvarchar](max) NULL,
	[trangThai] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhieuMuon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
-- Tạo khóa ngoại từ maTaiKhoan trong bảng PhieuMuon đến maTaiKhoan trong bảng TaiKhoan
ALTER TABLE [dbo].[PhieuMuon] WITH CHECK ADD FOREIGN KEY([maTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([maTaiKhoan])
GO
ALTER TABLE [dbo].[PhieuMuon] ADD CONSTRAINT CK_PhieuMuon_soLuongSach CHECK (dbo.CheckSoLuongSach(maTaiKhoan, soLuongSach) = 1);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****** CHI TIẾT PHIẾU MƯỢN ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuMuon](
	[maPhieuMuon] [varchar](5) NOT NULL,
	[maSach] [varchar](5) NOT NULL,
	[ngayThucTra] [date] NULL,
	[tienPhat] [money] NULL,
	[tinhTrangSach] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhieuMuon] ASC,
    [maSach] ASC
)  WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietPhieuMuon] WITH CHECK ADD FOREIGN KEY([maPhieuMuon])
REFERENCES [dbo].[PhieuMuon] ([maPhieuMUon])
GO

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/******* PHIẾU NHẬP SÁCH ******/
CREATE TABLE [dbo].[PhieuNhapSach](
    [maPhieuNhap] [char](13) NOT NULL,
    [ngayNhap] [date] NULL,
    [nhaCungCap] [char](10) NULL,
    PRIMARY KEY CLUSTERED
    (
        [maPhieuNhap] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/******* CHI TIẾT PHIẾU NHẬP SÁCH *********/
CREATE TABLE ChiTietPhieuNhapSach(
    [maPhieuNhap] [char](13) NOT NULL,
    [maSach] [varchar](5) NOT NULL,
    [tenSach] [nvarchar](100) NOT NULL,
    [maTacGia] [nvarchar](100) NOT NULL,
    [maTheLoai] [char](10) NOT NULL,
    [NXB] [nvarchar](100) NOT NULL,
    [namXuatBan] [int] NOT NULL,
    [soLuongNhap] [int] NOT NULL,
	[giaNhap] [money] NULL
    PRIMARY KEY CLUSTERED
    (
        [maPhieuNhap] ASC,
		[maSach] ASC 
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
-- Tạo ràng buộc khóa ngoại maPhieuNhap tham chiếu đến PhieuNhapSach
ALTER TABLE [dbo].[ChiTietPhieuNhapSach] WITH CHECK ADD FOREIGN KEY([maPhieuNhap])
REFERENCES [dbo].[PhieuNhapSach] ([maPhieuNhap])
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Thêm constraint CHECK cho cột `soLuongNhap` trong bảng `ChiTietPhieuNhapSach`
ALTER TABLE [dbo].[ChiTietPhieuNhapSach]
ADD CONSTRAINT CK_ChiTietPhieuNhapSach_soLuongNhap
CHECK (soLuongNhap >= 0);
-- Thêm index cho cột `maSach` trong bảng `ChiTietPhieuNhapSach`
CREATE INDEX IX_ChiTietPhieuNhapSach_maSach ON [dbo].[ChiTietPhieuNhapSach] (maSach);
GO 
-- Thêm comment cho thủ tục lưu trữ `sp_ThemSach`
CREATE PROCEDURE sp_ThemSach
    @maSach varchar(5),
    @tenSach nvarchar(100),
    @maTacGia char(10),
    @maTheLoai char(10),
    @NXB nvarchar(100),
    @namXuatBan int,
    @soLuongNhap int
AS
BEGIN
    -- Thêm một sách mới vào bảng `Kho Sach`
    INSERT INTO [dbo].[KhoSach] ([maSach], [tongSoLuong]) VALUES (@maSach, @soLuongNhap)
	-- Thêm một sách mới vào bảng `Thông Tin Sách`
	INSERT INTO [dbo].[ThongTinSach] ([maSach], [tenSach], [maTacGia], [maTheLoai], [NXB], [namXuatBan])VALUES (@maSach, @tenSach, @maTacGia, @maTheLoai, @NXB, @namXuatBan)
    -- Trả về mã sách của sách mới được thêm vào
    SELECT @maSach AS maSach;
END;
GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/***** THANH LÝ SÁCH *****/
CREATE TABLE [dbo].[ThanhLySach] (
    [maThanhLySach] [varchar](5) NOT NULL,
    [maSach] [varchar](5) NOT NULL,
    [soLuongSachHong] [int] NULL,
    [lyDoThanhLy] [nvarchar](100) NULL,
    [ngayThanhLy] [date] NULL,
    [ghiChu] [text] NULL,
    [tongTienThanhLy] [money] NOT NULL,
    PRIMARY KEY CLUSTERED (
        [maThanhLySach] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
ALTER TABLE [dbo].[ThanhLySach] WITH CHECK ADD FOREIGN KEY([maSach])
REFERENCES [dbo].[KhoSach] ([maSach])
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****** TỰ ĐỘNG TĂNG MÃ SÁCH  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE function [dbo].[func_nextID](@lastID varchar(5), @prefix varchar(2), @size int)
	returns varchar(5)
as
	BEGIN
		if(@lastID = '')
			set @lastID = @prefix + REPLICATE(0, @size - LEN(@prefix))
		declare @num_nextID int, @nextID varchar(5)
		set @lastID = LTRIM(RTRIM(@lastID))
		set @num_nextID = REPLACE(@lastID, @prefix, '') + 1
		set @size = @size - LEN(@prefix)
		set @nextID = @prefix + RIGHT(REPLICATE(0, @size) + CONVERT(VARCHAR(MAX), @num_nextID), @size)
		return  @nextID
	END
GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/************** TÍNH SỐ NGÀY TRẢ SÁCH TRỄ ****/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE function [dbo].[tinhSoNgayTre](@ngayMuon date, @ngayTra date, @songayMuon int)
	returns int
as
	BEGIN
		declare @num int = (DAY(@ngayTra) - DAY(@ngayMuon)) - @songayMuon
		return @num
	END
GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/****************** TỰ ĐỘNG TẠO MÃ SÁCH VÀ MÃ PHIẾU MƯỢN MỚI **********************/
CREATE trigger tr_nextSach on dbo.ThongTinSach
for insert
as
	begin
		declare @lastSach varchar(5)
		set @lastSach = (Select top 1 maSach from ThongTinSach order by maSach desc)
		UPDATE ThongTinSach set maSach = dbo.func_nextID(@lastSach, 'S', 5) where maSach = ''
	end
GO
create trigger tr_nextMaPM on dbo.PhieuMuon
for insert
as
	begin
		declare @lastIdMaPM varchar(5)
		set @lastIdMaPM = (Select top 1 maPhieuMuon from PhieuMuon order by maPhieuMuon desc)
		UPDATE PhieuMuon set maPhieuMuon = dbo.func_nextID(@lastIdMaPM, 'PM', 5) where maPhieuMuon = ''
	end
GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/************************** IN PHIẾU MƯỢN ******************************/
CREATE PROCEDURE sp_inPhieuMuon
AS
BEGIN
    -- Lấy thông tin phiếu mượn mới
    DECLARE @maPhieuMuon varchar(5)
    DECLARE @ngayMuon date
    DECLARE @soNgayMuon int
    DECLARE @maTaiKhoan char(10)
    DECLARE @maQuanLy char(10)
    DECLARE @ghiChu nvarchar(max)
    DECLARE @trangThai nvarchar(100)

    SELECT @maPhieuMuon = maPhieuMuon,
           @ngayMuon = ngayMuon,
           @soNgayMuon = soNgayMuon,
           @maTaiKhoan = maTaiKhoan,
           @maQuanLy = maQuanLy,
           @ghiChu = ghiChu,
           @trangThai = trangThai
    FROM PhieuMuon
    WHERE maPhieuMuon = (SELECT top 1 maPhieuMuon
                        FROM PhieuMuon
                        ORDER BY maPhieuMuon DESC);

    -- In phiếu mượn
    PRINT 'Mã phiếu mượn: ' + @maPhieuMuon
    PRINT 'Ngày mượn sách: ' + CONVERT(nvarchar(10), @ngayMuon, 103)
    PRINT 'Số ngày mượn: ' + @soNgayMuon
    PRINT 'Mã tài khoản: ' + @maTaiKhoan
    PRINT 'Mã quản lý: ' + @maQuanLy
    PRINT 'Ghi chú: ' + @ghiChu
    PRINT 'Trạng thái: ' + @trangThai
END
GO

GO

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/******************* TRIGGER CẬP NHẬT SỐ LƯỢNG MƯỢN **********************/
CREATE trigger tr_soLuongMuon on ChiTietPhieuMuon 
for insert 
as
	BEGIN
		update TaiKhoan
		set	soluongMuon = soluongMuon + 1
		from TaiKhoan, PhieuMuon, inserted
		where inserted.maPhieuMuon = PhieuMuon.maPhieuMuon and TaiKhoan.maTaiKhoan = PhieuMuon.maTaiKhoan
	END
GO

CREATE trigger tr_soLuongMuon_delete on ChiTietPhieuMuon 
for delete 
as
	BEGIN
		update TaiKhoan
		set	soluongMuon = soluongMuon - 1
		from TaiKhoan, PhieuMuon, inserted
		where inserted.maPhieuMuon = PhieuMuon.maPhieuMuon and TaiKhoan.maTaiKhoan = PhieuMuon.maTaiKhoan
	END
GO

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/**************************** TRIGGER TÍNH TIỀN PHẠT ************************/
CREATE PROCEDURE sp_TinhTienPhatHuuSachVaTre
    @maPhieuMuon varchar(5),
    @ngayTra date,
    @soLuongSachHong int,
    @matDoHai int,  -- Mức độ hư hỏng (1, 2 hoặc 3)
    @matSach bit  -- 1 nếu mất sách, 0 nếu không mất
AS
BEGIN
    DECLARE @giaTienSach money;
    DECLARE @soNgayTre int;
    DECLARE @tienPhat money;
    DECLARE @tienBoiThuong money;
    DECLARE @tinhTrangSach nvarchar(100); -- Tình trạng sách

    -- Lấy tình trạng sách từ bảng ChiTietPhieuMuon dựa trên maPhieuMuon
    SELECT @tinhTrangSach = tinhTrangSach
    FROM ChiTietPhieuMuon
    WHERE maPhieuMuon = @maPhieuMuon;

    -- Lấy giá tiền của sách từ bảng ThongTinSach
    SELECT @giaTienSach = giaTienSach
    FROM ThongTinSach
    WHERE maSach = (SELECT maSach FROM ChiTietPhieuMuon WHERE maPhieuMuon = @maPhieuMuon);

    -- Tính số ngày trễ trả sách
    SET @soNgayTre = DATEDIFF(day, @ngayTra, GETDATE());

    -- Tính tiền trễ hạn
    IF @soNgayTre > 0
    BEGIN
        IF @tinhTrangSach = 'Hư hỏng'
            SET @tienPhat = @soNgayTre * 10000;  
        ELSE IF @tinhTrangSach = 'Mất sách'
            SET @tienPhat = @soNgayTre * 10000;  
        ELSE
            SET @tienPhat = @soNgayTre * 10000;   
    END
    ELSE
    BEGIN
        SET @tienPhat = 0;
    END

    -- Tính tiền phạt mức độ hư hỏng
    IF @matDoHai = 1
        SET @tienBoiThuong = @soLuongSachHong * 0.05 * @giaTienSach;  -- Mức độ 1: 5% giá trị sách
    ELSE IF @matDoHai = 2
        SET @tienBoiThuong = @soLuongSachHong * 0.25 * @giaTienSach;  -- Mức độ 2: 25% giá trị sách
    ELSE IF @matDoHai = 3
        SET @tienBoiThuong = @soLuongSachHong * 0.75 * @giaTienSach;  -- Mức độ 3: 75% giá trị sách

    -- Nếu sách mất, thì cộng thêm 120% giá trị sách vào tiền phạt
    IF @matSach = 1
    BEGIN
        SET @tienPhat = @tienPhat + 1.2 * @giaTienSach;  -- 120% giá trị sách
    END
    -- Tính tổng tiền phạt
    SET @tienPhat = @tienPhat + @tienBoiThuong;

    -- Trả về tổng tiền phạt
    SELECT @tienPhat AS TongTienPhat;
END;
GO

GO
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/************************* IN PHIẾU TRẢ ,CẬP NHẬT SỐ LƯỢNG SÁCH VÀ TÌNH TRẠNG SÁCH ****************/
CREATE TRIGGER UpdateTienPhat
ON dbo.ChiTietPhieuMuon
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @maPhieuMuon varchar(5), @maSach varchar(5), @ngayThucTra date, @tinhTrangSach nvarchar(100), @tinhTrangSachHienTai nvarchar(100);
    DECLARE @giaTienSach money, @tienPhat money, @tienboithuong money, @soLuongMuon int, @soNgayTre  int;

    -- Lấy thông tin từ bảng Inserted
    SELECT @maPhieuMuon = i.maPhieuMuon, @maSach = i.maSach, @ngayThucTra = i.ngayThucTra, @tinhTrangSach = i.tinhTrangSach
    FROM inserted i;

    -- Lấy giá tiền sách từ bảng ThongTinSach
    SELECT @giaTienSach = ts.giaTienSach
    FROM dbo.ThongTinSach ts
    WHERE ts.maSach = @maSach;
    
    IF UPDATE(ngayThucTra) and update (tinhTrangSach)
	BEGIN
		-- Tính toán số ngày trễ hạn
		SET @soNgayTre = DATEDIFF(day, (SELECT hanTraSach FROM dbo.PhieuMuon WHERE maPhieuMuon = @maPhieuMuon), @ngayThucTra);
		-- Tính tiền trễ hạn
		IF @soNgayTre > 0
		BEGIN
			
				SET @tienPhat = @soNgayTre * 10000;   
		END
		ELSE
		BEGIN
			SET @tienPhat = 0;
		END
		-- Tính toán tiền phạt dựa trên điều kiện và số ngày trễ hạn
		IF @tinhTrangSach = N'Mất sách'
			SET @tienboithuong = @giaTienSach * 1.4 ;
		ELSE IF @tinhTrangSach = N'Bị hỏng'
			SET @tienboithuong = @giaTienSach * 0.5 ; 
		ELSE
			SET @tienboithuong = 0;

		-- Cập nhật tienPhat trong bảng ChiTietPhieuMuon
		UPDATE dbo.ChiTietPhieuMuon
		SET tienPhat = @tienPhat + @tienboithuong
		WHERE maPhieuMuon = @maPhieuMuon AND maSach = @maSach;

		-- Cập nhật soLuongMuon trong bảng TaiKhoan
		UPDATE dbo.TaiKhoan
		SET soLuongMuon = soLuongMuon - 1
		WHERE maTaiKhoan = (SELECT maTaiKhoan FROM dbo.PhieuMuon WHERE maPhieuMuon = @maPhieuMuon);

		-- Cập nhật KhoSach
		IF @tinhTrangSach IN ('Bị hỏng')
			UPDATE dbo.KhoSach
			SET soLuongSachHong = soLuongSachHong + 1
			WHERE maSach = @maSach;
		ELSE IF @tinhTrangSach = N'không hỏng'
			UPDATE dbo.KhoSach
			SET soLuongCon = soLuongCon + 1
			WHERE maSach = @maSach;
	end
	IF update (tinhTrangSach)
	BEGIN
		--lấy tình trạng hiện tại
		SELECT @tinhTrangSachHienTai = i.tinhTrangSach
		FROM inserted i;
		SET @soNgayTre = DATEDIFF(day, (SELECT hanTraSach FROM dbo.PhieuMuon WHERE maPhieuMuon = @maPhieuMuon), @ngayThucTra);
		-- Tính tiền trễ hạn
		IF @soNgayTre > 0
		BEGIN
			
			SET @tienPhat = @soNgayTre * 10000;   
		END
		ELSE
		BEGIN
			SET @tienPhat = 0;
		END
		-- Tính toán tiền phạt dựa trên điều kiện và số ngày trễ hạn
		IF @tinhTrangSach = N'Mất sách'
			SET @tienboithuong = @giaTienSach * 1.4 ;
		ELSE IF @tinhTrangSach = N'Bị hỏng'
			SET @tienboithuong = @giaTienSach * 0.5 ; 
		ELSE
			SET @tienboithuong = 0;

		-- Cập nhật tienPhat trong bảng ChiTietPhieuMuon
		UPDATE dbo.ChiTietPhieuMuon
		SET tienPhat = @tienPhat + @tienboithuong
		WHERE maPhieuMuon = @maPhieuMuon AND maSach = @maSach;
	end
END
go
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/**************** TỰ ĐỘNG TẠO PHIẾU NHẬP MỚI VÀ CẬP NHẬT SỐ LƯỢNG SÁCH TRONG KHO ****************/
CREATE TRIGGER tr_nextMaPhieuNhap ON dbo.PhieuNhapSach
FOR INSERT
AS
BEGIN
    DECLARE @lastIdMaPhieuNhap char(13)
    SET @lastIdMaPhieuNhap = (SELECT TOP 1 maPhieuNhap FROM PhieuNhapSach ORDER BY maPhieuNhap DESC)
    
    -- Tạo mã phiếu nhập sách mới và cập nhật vào bảng PhieuNhapSach
    UPDATE PhieuNhapSach SET maPhieuNhap = dbo.func_nextID(@lastIdMaPhieuNhap, 'PN', 5) WHERE maPhieuNhap = ''
END
go
CREATE TRIGGER tr_updateSoLuongNhapSach ON dbo.ChiTietPhieuNhapSach
FOR INSERT
AS
BEGIN
    DECLARE @maSach varchar(5)
    DECLARE @soLuongNhap int
    
    -- Lấy thông tin từ chi tiết phiếu nhập sách
    SELECT @maSach = i.maSach, @soLuongNhap = i.soLuongNhap
    FROM inserted i

    -- Cập nhật số lượng sách trong bảng ThongTinSach
    UPDATE dbo.KhoSach
    SET soLuongCon = soLuongCon + @soLuongNhap
    WHERE maSach = @maSach
END
GO 
/******************************* TỰ ĐỘNG TĂNG MÃ Thanh lý *******************/
CREATE TABLE ThanhLySachCounter (
    Counter INT
);

INSERT INTO ThanhLySachCounter (Counter) VALUES (1);

Go
CREATE TRIGGER tr_ThanhLySach_Insert
ON [dbo].[ThanhLySach]
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Counter INT;
    DECLARE @Prefix NVARCHAR(2) = 'TL';

    SELECT @Counter = Counter FROM ThanhLySachCounter;

    INSERT INTO [dbo].[ThanhLySach] (
        [maThanhLySach],
        [maSach],
        [soLuongSachHong],
        [lyDoThanhLy],
        [ngayThanhLy],
        [ghiChu],
        [tongTienThanhLy]
    )
    SELECT
        @Prefix + RIGHT('000' + CAST(@Counter AS NVARCHAR(3)), 3),
        [maSach],
        [soLuongSachHong],
        [lyDoThanhLy],
        [ngayThanhLy],
        [ghiChu],
        [tongTienThanhLy]
    FROM inserted;

    SET @Counter = @Counter + 1;

    UPDATE ThanhLySachCounter SET Counter = @Counter;
END;
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/******************************* TỰ ĐỘNG TĂNG SỐ LƯỢNG SÁCH HƯ *******************
CREATE TRIGGER tr_updateSoLuongSachHong ON dbo.KhoSach
FOR INSERT, UPDATE, DELETE
AS
BEGIN
    DECLARE @maSach varchar(5)
    DECLARE @soLuongSachHong int 
    DECLARE @soLuongSachHongCu int 

    SELECT @maSach = i.maSach, @soLuongSachHong = i.soLuongSachHong
    FROM inserted i

    SELECT @soLuongSachHongCu = KhoSach.soLuongSachHong
    FROM KhoSach
    WHERE maSach = @maSach

    UPDATE KhoSach
    SET soLuongSachHong = ISNULL(@soLuongSachHongCu, 0) + @soLuongSachHong
    WHERE maSach = @maSach

    SELECT @maSach = d.maSach, @soLuongSachHong = d.soLuongSachHong
    FROM deleted d

    SELECT @soLuongSachHongCu = KhoSach.soLuongSachHong
    FROM KhoSach
    WHERE maSach = @maSach

    UPDATE KhoSach
    SET soLuongSachHong = ISNULL(@soLuongSachHongCu, 0) + @soLuongSachHong
    WHERE maSach = @maSach
END
*/
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
GO
/**************** DANH MỤC ****************/
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM001', N'Chuyên ngành Điện-Điện tử')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM002', N'Chuyên ngành Cơ khí')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM003', N'Chuyên ngành Công nghệ thông tin')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM004', N'Chuyên ngành Xây dựng')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM005', N'Sách Tiếng Anh')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM006', N'Kỹ năng sống')
INSERT [dbo].[DanhMucSach] ([maDMSach], [tenDMSach]) VALUES (N'DM007', N'Sách nước ngoài')
/******************* TÁC GIẢ ****************/
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG001', 'Hoàng Thị Mỹ Lệ', 3)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG002', 'Nguyễn Linh Nam', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG003', 'Lê Văn Sung', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG004', 'Nguyễn Đức Lợi', 4)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG005', 'Võ Huy Hoàng', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG006', 'Nguyễn Như Hiền', 4)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG007', 'Nguyễn Đức Trí', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG008', 'Phạm Ngọc Thắng', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG009', 'Đặng Văn Tuệ', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG010', 'Nguyễn Xuân Phú', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG011', 'Tăng Văn Mùi - Trần Duy Nam', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG012', 'Nguyễn Công Phương', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG013', 'Lưu Bá Thuận', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG014', 'Nguyễn Thái', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG015', 'Bộ Xây dựng', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG016', 'GS.TS.Phạm Văn Hội', 1)
INSERT INTO [dbo].[TacGia]([maTacGia], [tenTacGia], [soSach]) VALUES ('TG017', 'Phan Quang Minh', 1)

/**************** PHÂN LOẠI ********************/
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL001', N'Giáo trình công nghệ ')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL002', N'Giáo trình kỹ thuật')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL003', N'Ngôn ngữ')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL004', N'Điện - Điện tử')
INSERT [dbo].[PhanLoaiSach] ([maTheLoai],[tenTheLoai]) VALUES (N'TL005', N'Cơ sở hạ tầng')
/******************* KHO SÁCH ****************************/
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0003',7,5,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0001',10,7,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0002',25,15,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0004',15,9,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0005',10,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0006',6,3,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0007',8,4,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0008',3,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0009',3,3,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0010',3,0,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0011',23,7,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0012',14,9,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0013',9,5,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0014',32,13,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0015',28,11,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0016',5,1,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0017',4,1,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0018',7,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0019',5,3,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0020',6,6,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0021',10,9,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0022',4,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0023',2,2,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0024',7,5,0)
INSERT [dbo].[KhoSach] ([maSach], [tongSoLuong], [soLuongCon], [soLuongSachHong]) VALUES (N'S0025',3,2,0)
/******************* THÔNG TIN SÁCH ***********************/
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0001', N'Giáo trình kỹ thuật xung số và ứng dụng', N'DM003', N'TL001',N'TG002',N'NXB Công nghệ thông tin', 2016, 125000,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0002', N'Lập trình cơ bản với C', N'DM003', N'TL001',N'TG001', N'NXB Công nghệ thông tin', 2016, 110000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0003', N'Trường điện từ - Lý thuyết và bài tập', N'DM003', N'TL001',N'TG003', N'NXB Công nghệ thông tin', 2016, 90000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0004', N'Cơ sở dữ liệu', N'DM003', N'TL001',N'TG001', N'NXB Công nghệ thông tin', 2016, 135000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0005', N'Tin học văn phòng', N'DM003', N'TL001',N'TG001',  N'NXB Công nghệ thông tin', 2016, 73000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0006', N'Bơm nhiệt', N'DM002', N'TL002',N'TG004',  N'NXB Cơ Khí', 2018, 95000,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0007', N'Cơ sở thiết kế máy', N'DM002', N'TL002',N'TG004',  N'NXB Cơ Khí', 2018,100000 ,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0008', N'Đo lường nhiệt', N'DM002', N'TL002',N'TG005',  N'NXB Cơ Khí', 2018, 58000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0009', N'Nhiên liệu sạch', N'DM002', N'TL002',N'TG004',  N'NXB Cơ Khí', 2018,60000,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0010', N'Giáo trình kỹ thuật nhiệt', N'DM002', N'TL002',N'TG004', N'NXB Cơ Khí', 2018, 112000,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0011', N'Ngoại ngữ 1', N'DM005', N'TL003',N'TG006',  N'NXB Ngoại ngữ', 2010, 145000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0012', N'Grammar in use', N'DM005', N'TL003',N'TG007', N'NXB Ngoại ngữ', 2018, 123000, N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0013', N'Listen carefully', N'DM005', N'TL003',N'TG006',  N'NXB Ngoại ngữ', 2018, 123000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0014', N'Ngoại ngữ cơ bản', N'DM005', N'TL003',N'TG006',  N'NXB Ngoại ngữ', 2018, 105000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0015', N'Ngoại ngữ 2', N'DM005', N'TL003',N'TG006',  N'NXB Ngoại ngữ', 2018, 145000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0016', N'Kỹ thuật xử lý tín hiệu điều khiển', N'DM001', N'TL004',N'TG008',  N'NXB Điện-Điện Tử', 2014, 598000 ,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0017', N'Bài tập vi điều khiển và PLC', N'DM001', N'TL004',N'TG009',  N'NXB Điện-Điện Tử', 2014, 78000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0018', N'Khí cụ điện - kết cấu, sử dụng và sửa chữa', N'DM001', N'TL004',N'TG010',  N'NXB Điện-Điện Tử', 2014, 132000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0019', N'Sổ tay chuyên ngành điện', N'DM001', N'TL004',N'TG011',N'NXB Điện-Điện Tử', 2013, 54000 ,N' Bình thường ',N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0020', N'Bài tập điều khiển tự động', N'DM001', N'TL004',N'TG012',  N'NXB Điện-Điện Tử', 2013,97000,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0021', N'Sổ tay máy làm đất và làm đường', N'DM004', N'TL005',N'TG013',  N'NXB Xây dựng', 2015, 54000,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0022', N'Móng cọc phân tích và thiết kế', N'DM004', N'TL005',N'TG014',  N'NXB Xây dựng', 2014, 92000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0023', N'Cấu tạo bê tông cốt thép', N'DM004', N'TL005',N'TG015', N'NXB Xây dựng', 2014, 167000,N' Bình thường ', N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0024', N'Kết cấu thép - Công trình đặc biệt', N'DM004', N'TL005',N'TG016', N'NXB Xây dựng', 2013, 127000,N' Bình thường ',  N'')
INSERT [dbo].[ThongTinSach] ([maSach], [tenSach], [maDMSach], [maTheLoai],[maTacGia], [NXB], [namXuatBan],[giaTienSach],[tinhTrangSach], [tomTatND]) VALUES (N'S0025', N'Kết cấu bê tông cốt thép - Phần cấu kiện cơ bản', N'DM004', N'TL005',N'TG017',  N'NXB Xây dựng', 2013, 125000,N' Bình thường ', N'')
/************************** TÀI KHOẢN *************************/
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312141001', N'abc123', N'Học Sinh - Sinh Viên', N'Phạm Văn Tâm', CAST(N'2001-09-02' AS Date), N'Nam', N'phamvantam@gmail.com', N'0776155064', N'495/18 Tô Hiến Thành, Phường 14 , Quận 10 ', 1, 3)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312141002', N'abc123', N'Học Sinh - Sinh Viên', N'Hà Nguyễn Yến Vy', CAST(N'2001-07-09' AS Date), N'Nữ', N'hanguyenyenvy@gmail.com', N'0777443873',N' 336 Trần Xuân Soạn, Tân Hưng , Quận 7' ,1, 1)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312041001', N'abc123', N'Cán Bộ Giảng Viên', N'Nguyễn Bá Sĩ Trâm', CAST(N'2001-07-09' AS Date), N'Nam', N'nguyenbasitram@gmail.com', N'0795841141',N' 793 Trần Xuân Soạn, Tân Hưng , Quận 7 ', 1, 0)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312141003', N'abc123', N'Học Sinh - Sinh Viên', N'Phan Thị Anh Thư', CAST(N'2001-07-09' AS Date), N'Nữ', N'phanthianhthu@gmail.com', N'0375141345',N'628/12 Phan Văn Trị ,Phường 10, Gò Vấp', 1, 0)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312041002', N'abc123', N'Cán Bộ Giảng Viên', N'Nguyễn Thanh Tiến', CAST(N'2001-07-09' AS Date), N'Nam', N'thanhtien123@gmail.com', N'0779997865', N'235/2 Nguyễn Văn Cừ, Phường Nguyễn Cư Trinh , Quận 1', 1, 3)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312141004', N'abc123', N'Học Sinh - Sinh Viên', N'Trương Tiến Anh', CAST(N'2001-07-09' AS Date), N'Nam', N'tienanh7723@gmail.com', N'0901345667', N' 1050 Tạ Quang Bửu, Phường 6 , Quận 8',1, 3)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312141005', N'abc123', N'Học Sinh - Sinh Viên', N'Nguyễn Quang Tuấn Nghĩa', CAST(N'2001-07-09' AS Date), N'Nam', N'tuannghia@gmail.com', N'0366123421',N' 74 Trần Hưng Đạo, Phường 7, Quận 5', 1, 3)
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [matKhau], [loaiTaiKhoan], [tenNguoiDung], [ngaySinh], [gioiTinh], [email], [sdt], [diaChi], [trangThai], [soLuongMuon]) VALUES (N'312141006', N'abc123', N'Học Sinh - Sinh Viên', N'Trần Hồng Quang', CAST(N'2001-07-09' AS Date), N'Nam', N'hongquang@gmail.com', N'0789901451', N'1001 Đường Cách Mạng Tháng 8 , Phường 5, Quận Tân Bình', 1, 3)
/************************** LOẠI THẺ ***************************/
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312141001', N'Học Sinh - Sinh Viên', CAST(N'2023-01-01' AS Date), CAST(N'2023-12-31' AS Date ), 10, 20, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312141002', N'Học Sinh - Sinh Viên', CAST(N'2023-01-05' AS Date), CAST(N'2023-12-31' AS Date ), 10, 20, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312041001', N'Cán Bộ Giảng Viên', CAST(N'2023-01-13' AS Date), CAST(N'2023-12-31' AS Date ), 25, 90, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312141003', N'Học Sinh - Sinh Viên', CAST(N'2023-02-03' AS Date), CAST(N'2024-02-03' AS Date ), 10, 20, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312041002', N'Cán Bộ Giảng Viên', CAST(N'2023-02-10' AS Date), CAST(N'2024-02-10' AS Date ), 25, 90, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312141004', N'Học Sinh - Sinh Viên', CAST(N'2023-01-20' AS Date), CAST(N'2023-12-31' AS Date ), 10, 20, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312141005', N'Học Sinh - Sinh Viên', CAST(N'2023-03-18' AS Date), CAST(N'2024-03-30' AS Date ), 10, 20, 100000)
INSERT INTO [dbo].[LoaiThe] ([maLoaiThe], [tenLoaiThe], [ngayMoThe], [hanSuDung], [soSachDuocMuon], [thoiGianDuocMuonToiDa], [giaTienGiaHan]) VALUES (N'312141006', N'Học Sinh - Sinh Viên', CAST(N'2023-09-05' AS Date), CAST(N'2024-09-30' AS Date ), 10, 20, 100000)
/************************** QUẢN LÝ ***************************/
INSERT [dbo].[QuanLy] ([maQuanLy], [matKhau], [tenQuanLy], [ngaySinh], [gioiTinh], [diaChi], [sdt], [email], [trangThai]) VALUES (N'101010', N'abc123', N'ADMIN', CAST(N'1990-07-02' AS Date), N'Nam', N'TP.Hồ Chí Minh', N'0773123889', N'admin@gmail.com', 1)
INSERT [dbo].[QuanLy] ([maQuanLy], [matKhau], [tenQuanLy], [ngaySinh], [gioiTinh], [diaChi], [sdt], [email], [trangThai]) VALUES (N'101011', N'abc123', N'THỦ THƯ', CAST(N'1997-02-28' AS Date), N'Nữ', N'TP.Hồ Chí Minh', N'0795841141', N'thuthu@gmail.com', 1)
/************************** PHIẾU MƯỢN ****************************/
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM001', CAST(N'2023-08-10' AS Date), 7, CAST(N'2023-08-17' AS Date), 1 , N'312141002', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM002', CAST(N'2023-08-11' AS Date), 7, CAST(N'2023-08-18' AS Date), 1 , N'312141004', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM003', CAST(N'2023-08-12' AS Date),12, CAST(N'2023-08-24' AS Date), 1 , N'312141006', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM004', CAST(N'2023-08-24' AS Date), 7, CAST(N'2023-08-31' AS Date), 1 , N'312141001', N'101010 ', N'', N'Đã trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM005', CAST(N'2023-08-24' AS Date), 7, CAST(N'2023-08-31' AS Date), 1 , N'312041001', N'101010 ', N'', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM006', CAST(N'2023-08-24' AS Date), 7, CAST(N'2023-08-31' AS Date), 1 , N'312141004', N'101010 ', N'', N'Đã trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM007', CAST(N'2023-08-25' AS Date), 7, CAST(N'2023-09-01' AS Date), 1 , N'312141005', N'101010 ', N'Không', N'Chưa trả')
INSERT [dbo].[PhieuMuon] ([maPhieuMuon], [ngayMuon], [soNgayMuon], [hanTraSach], [soLuongSach], [maTaiKhoan], [maQuanLy], [ghiChu], [trangThai]) VALUES (N'PM008', CAST(N'2023-08-20' AS Date), 7, CAST(N'2023-08-17' AS Date), 1 , N'312141006', N'101010 ', N'', N'Chưa trả')
/******************** CHI TIẾT PHIẾU MƯỢN ******************/
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM001', N'S0001', CAST(N'2023-08-17' AS Date), 137500.0000, N'Mất sách')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM001', N'S0002', CAST(N'2022-08-20' AS Date), 10000.0000, 1)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM002', N'S0003', CAST(N'2023-08-20' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM002', N'S0004', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM002', N'S0005', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM003', N'S0006', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM004', N'S0006', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM004', N'S0008', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM004', N'S0009', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM005', N'S0006', CAST(N'2023-08-30' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM005', N'S0008', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM006', N'S0002', CAST(N'2023-09-04' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM006', N'S0003', CAST(N'2023-09-04' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM006', N'S0007', CAST(N'2023-09-04' AS Date), 0.0000, 0)
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM007', N'S0003', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM007', N'S0005', NULL, 0.0000, N'')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM008', N'S0007', CAST(N'2023-09-10' AS Date), 70000.0000, N'Trễ hạn')
INSERT [dbo].[ChiTietPhieuMuon] ([maPhieuMuon], [maSach], [ngayThucTra], [tienPhat], [tinhTrangSach]) VALUES (N'PM008', N'S0010', CAST(N'2023-09-10' AS Date), 70000.0000, N'Trễ hạn')
/**************************** PHIẾU NHẬP SÁCH ************************/
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [ngayNhap], [nhaCungCap]) VALUES ('PN001', CAST(N'2023-08-01' AS Date), 'NCC001')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [ngayNhap], [nhaCungCap]) VALUES ('PN002', CAST(N'2023-08-08' AS Date), 'NCC002')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [ngayNhap], [nhaCungCap]) VALUES ('PN003', CAST(N'2023-08-09' AS Date), 'NCC003')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [ngayNhap], [nhaCungCap]) VALUES ('PN004', CAST(N'2023-08-10' AS Date), 'NCC004')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [ngayNhap], [nhaCungCap]) VALUES ('PN005', CAST(N'2023-08-11' AS Date), 'NCC005')
INSERT INTO [dbo].[PhieuNhapSach] ([maPhieuNhap], [ngayNhap], [nhaCungCap]) VALUES ('PN006', CAST(N'2023-08-12' AS Date), 'NCC006')
/**************************** CHI TIẾT PHIẾU NHẬP SÁCH ************************/
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES ('PN001',N'S0025','Lập trình Python nâng cao','TG001','TL001','NXB Công nghệ thông tin',2023,10,175000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES ('PN001',N'S0026','Lập trình Java nâng cao','TG001','TL002','NXB Giáo dục',2023,20,20600)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES ('PN001',N'S0027','Lập trình Go nâng cao','TG002' ,'TL003','NXB Công nghệ thông tin',2023,15,198000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES ('PN002',N'S0028','Toán cao cấp','TG005','TL001','NXB Giáo dục',2023,25,210000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES ('PN002',N'S0029','Vật lý đại cương','TG006','TL002','NXB Giáo dục',2023,15,176000)
INSERT INTO [dbo].[ChiTietPhieuNhapSach] ([maPhieuNhap],[maSach],[tenSach],[maTacGia],[maTheLoai],[NXB],[namXuatBan],[soLuongNhap],[giaNhap]) VALUES ('PN002',N'S0030','Hóa học đại cương','TG012','TL003','NXB Giáo dục',2023,15, 97000)

/************************** THANH LÝ SÁCH ********************************/
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES ('TL002', 'S0001', 2, N'Sách bị cũ', '2023-08-13', 'Không có',20000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES ('TL003', 'S0002', 3, N'Sách bị mốc', '2023-08-14', 'Không có',525000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES ('TL004', 'S0003', 4, N'Sách bị mất trang', '2023-08-15', 'Không có',150000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES ('TL005', 'S0004', 5, N'Sách bị lỗi in', '2023-08-16', 'Không có',224000)
INSERT INTO [dbo].[ThanhLySach] ([maThanhLySach], [maSach], [soLuongSachHong], [lyDoThanhLy], [ngayThanhLy], [ghiChu],[tongTienThanhLy]) VALUES ('TL006', 'S0005', 6, N'Sách bị cháy', '2023-08-17', 'Không có',632000)


ALTER TABLE [dbo].[QuanLy] ADD  DEFAULT ('1') FOR [trangThai]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  DEFAULT ('1') FOR [trangThai]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  DEFAULT ((0)) FOR [soLuongMuon]
GO
ALTER TABLE [dbo].[ChiTietPhieuMuon]  WITH CHECK ADD FOREIGN KEY([maSach])
REFERENCES [dbo].[ThongTinSach] ([maSach])
GO
ALTER TABLE [dbo].[PhieuMuon]  WITH CHECK ADD FOREIGN KEY([maQuanLy])
REFERENCES [dbo].[QuanLy] ([maQuanLy])
GO
ALTER TABLE [dbo].[PhieuMuon]  WITH CHECK ADD FOREIGN KEY([maTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([maTaiKhoan])
GO
ALTER TABLE [dbo].[ThongTinSach]  WITH CHECK ADD FOREIGN KEY([maDMSach])
REFERENCES [dbo].[DanhMucSach] ([maDMSach])
ALTER TABLE [dbo].[QuanLy]  WITH CHECK ADD CHECK  (([Email] like '[a-z]%@%'))
GO
ALTER TABLE [dbo].[QuanLy]  WITH CHECK ADD CHECK  (([SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR [SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD CHECK  (([Email] like '[a-z]%@%'))
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD CHECK  (([SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' OR [SDT] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
GO
-- Thêm giá trị mặc định cho cột `maDMSach` trong bảng `DanhMucSach`. Giá trị mặc định của cột `maDMSach` là `1`, có nghĩa là danh mục sách chưa được phân loại.
ALTER TABLE [dbo].[DanhMucSach] ADD  DEFAULT (1) FOR [maDMSach]
GO
-- Thêm giá trị mặc định cho cột `maTheLoai` trong bảng `PhanLoaiSach`. Giá trị mặc định của cột `maTheLoai` là `1`, có nghĩa là thể loại sách chưa được phân loại.
ALTER TABLE [dbo].[PhanLoaiSach] ADD  DEFAULT (1) FOR [maTheLoai]
GO
-- Thêm ràng buộc CHECK cho bảng `DanhMucSach`. Ràng buộc CHECK đảm bảo rằng giá trị của cột `tenDMSach` trong bảng `DanhMucSach` không được để trống.
ALTER TABLE [dbo].[DanhMucSach]  WITH CHECK ADD CHECK  (([tenDMSach] IS NOT NULL))
GO
-- Thêm ràng buộc CHECK cho bảng `PhanLoaiSach`. Ràng buộc CHECK đảm bảo rằng giá trị của cột `tenTheLoai` trong bảng `PhanLoaiSach` không được để trống.
ALTER TABLE [dbo].[PhanLoaiSach]  WITH CHECK ADD CHECK  (([tenTheLoai] IS NOT NULL))
GO
ALTER DATABASE [QuanLyThuVien] SET  READ_WRITE 
GO

